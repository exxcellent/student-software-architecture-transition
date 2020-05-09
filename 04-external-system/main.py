import numpy as np
import itertools
import math
import random
from datetime import datetime, timedelta
import googlemaps
import copy
from flask import Flask, jsonify, request
from flask_cors import CORS

ISO_8601_dt_format = '%Y-%m-%dT%H:%M:%S.%fZ'

def googlemapsAPI():
    return googlemaps.Client(key='<ENTER YOUR GOOGLE MAPS API KEY>')

def strftime_iso_8601(dt):
    return dt.strftime(ISO_8601_dt_format)[:-4] + 'Z'


def datetime_range(start, max_duration=60 * 11, step_size=60 * 6):
    return [start + timedelta(minutes=x) for x in range(0, max_duration, step_size)]


def gmaps_dist_matrix(departure_time, locations):
    return googlemapsAPI().distance_matrix(origins=locations, destinations=locations, departure_time=departure_time)


def gmaps_dist_array_random(n):
    return {'elements': [{'distance': {'text': '0 m', 'value': 0}, 'duration': {'text': '0 min', 'value': 0},
                          'duration_in_traffic': {'text': '0 min', 'value': random.randint(5 * 60, 30 * 60)},
                          'status': 'OK'} for x in range(n)]}


def gmaps_dist_matrix_random(n):
    return {'rows': [gmaps_dist_array_random(n) for x in range(n)]}


def calculate_trip(sequenceFloat, locations, departure_time, distance_matrices, query_datetimes):
    locations = copy.deepcopy(locations)
    sequence = sequenceFloat.astype(np.int64)
    route_travel_time = 0
    time = departure_time
    locations[sequence[0]]['eta'] = strftime_iso_8601(time)

    for s in zip(sequence, np.delete(sequence, 0)):
        dm_idx = query_datetimes.index(max(x for x in query_datetimes if x <= time))
        dm = distance_matrices[dm_idx]
        travel_time = dm['rows'][s[0]]['elements'][s[1]]['duration_in_traffic']['value']
        locations[s[0]]['travel_time'] = travel_time / 60
        route_travel_time += travel_time
        time += timedelta(seconds=travel_time) + timedelta(minutes=locations[s[0]]['duration'])
        locations[s[1]]['eta'] = strftime_iso_8601(time)
    return {'travel_time': route_travel_time / 60, 'locations': np.array(locations)[sequence]}


def optimize(departure_time, locations, mode='brute_force'):
    n = len(locations)

    if mode == 'random' or n > 10:
        query_datetimes = [departure_time]
        distance_matrices = [gmaps_dist_matrix_random(n)]
    else:
        query_datetimes = datetime_range(departure_time)
        distance_matrices = [gmaps_dist_matrix(t, locations) for t in query_datetimes]

    if mode == 'none' or n > 11:
        solutions = [range(1, n - 1)]
    else:
        solutions = itertools.permutations(range(1, n - 1))

    best_travel_time = math.inf
    for r in solutions:
        sequence = np.append(np.insert(np.array(r), 0, 0), n - 1)
        trip = calculate_trip(sequence, locations, departure_time, distance_matrices, query_datetimes)
        travel_time = trip['travel_time']
        if travel_time < best_travel_time:
            best_travel_time = travel_time
            best_locations = trip['locations']
    return {'best_travel_time': best_travel_time, 'locations': best_locations}


app = Flask(__name__)
CORS(app)


@app.route('/optimize', methods=['POST'])
def optimize_handler():
    mode = request.args.get('mode')
    content = request.get_json()
    departure_time = datetime.strptime(content['departure_time'], ISO_8601_dt_format)
    if departure_time < datetime.now() + timedelta(minutes=15):
        departure_time = datetime.now() + timedelta(minutes=15)
        # return "INVALID_REQUEST (departure_time is in the past)", 400
    locations = content['locations']
    result = optimize(departure_time, locations, mode)
    response = jsonify({'departure_time': strftime_iso_8601(departure_time), 'travel_time': result['best_travel_time'],
                        'locations': result['locations'].tolist()})
    return response


@app.route('/', methods=['GET'])
def home_handler():
    return "swm-onpa-routenplaner is up and running."


if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')