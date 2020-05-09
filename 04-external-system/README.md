# Routenoptimierung und Berechnung der Fahrzeiten

> Falls echte Routen berechnet werden sollen, 
> muss der API-Key von Google Maps in main.py:12 
> eingetragen werden!
> Ohne den API-Key ist nur ?mode=random möglich.

Dieser Service basiert auf einem Prototypen zur optimierten Routenberechnung. 
Für die Bachelor-Arbeit wird nur der `random` Modus genutzt, 
um die Routenoptimierung zu simulieren.

## Getting started

`python3 main.py` startet einen REST-Service auf Port 5000.

### Beispiel

`POST http://localhost:5000/optimize?mode=random`
```
{
	"departure_time": "2020-05-09T18:11:05.203Z",
	"locations": [
		{
			"id": 1,
            "_index": 0,
            "lat": 48.118289,
            "lng": 11.641278,
            "duration": 0
		},
		{
			"id": 2,
            "_index": 1,
            "lat": 48.094597,
            "lng": 11.534644,
            "duration": 0
		}
	]
}
```

```
{
  "departure_time": "2020-05-09T18:14:22.788Z",
  "locations": [
    {
      "_index": 0,
      "duration": 0,
      "eta": "2020-05-09T18:14:22.788Z",
      "id": 1,
      "lat": 48.118289,
      "lng": 11.641278,
      "travel_time": 19.733333333333334
    },
    {
      "_index": 1,
      "duration": 0,
      "eta": "2020-05-09T18:34:06.788Z",
      "id": 2,
      "lat": 48.094597,
      "lng": 11.534644
    }
  ],
  "travel_time": 19.733333333333334
}
```

### Weitere Modi

- `none` Normale Routenberechnung über eine Distanz Matrix
- `random` Die Fahrzeiten werden zufällig gesetzt (keine Google API Nutzung)
- `brute_force` (default) Alle Kombinationen der Reihenfolge der Locations werden mit der normalen Routenberechnung ausprobiert und die Route mit der kleinsten `travel_duration` zurückgegeben.

## Google Maps API Key

Unter https://console.cloud.google.com/google/maps-apis/ muss ein API-Key erstellt werden und mindestens die [Distance Matrix API]( 
https://console.cloud.google.com/google/maps-apis/apis/distance-matrix-backend.googleapis.com) aktiviert werden.

Zum Anzeigen der Karte und der Routen-Berechnung im Client sind auch die
[Directions API](https://console.cloud.google.com/google/maps-apis/apis/directions-backend.googleapis.com) und die
[Maps JavaScript API](https://console.cloud.google.com/google/maps-apis/apis/maps-backend.googleapis.com)
notwendig.