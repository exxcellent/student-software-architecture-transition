import {Injectable} from '@angular/core';
import {exists, logWarn} from '@software-architecture-transition/shared';
// @ts-ignore
import {LatLngBoundsLiteral, LatLngLiteral} from '@agm/core';
import {WaypointRepositoryService} from '@software-architecture-transition/data-access/navigation';
import {WAYPOINT_ICONS} from '../waypoint-icons';
import {Observable} from 'rxjs';
import {Route, Waypoint} from '@software-architecture-transition/dialog-core/navigation';

@Injectable()
export class NavigationMapDialogCore {
  private directionsService: google.maps.DirectionsService;
  private directionsRenderer: google.maps.DirectionsRenderer;

  private centerLatLng: LatLngLiteral = { lat: 48.12738575287784, lng: 11.416353270520176 };

  private options = {
    zoomControl: false,
    mapTypeControl: false,
    scaleControl: false,
    streetViewControl: false,
    rotateControl: false,
    fullscreenControl: false,
    disableDefaultUi: false,
    zoom: 8
  };

  private boundary: LatLngBoundsLiteral = {
    south: 47.91289510668061,
    west: 10.856841242980863,
    north: 48.34187639907507,
    east: 11.975865298059489
  };

  private type: 'roadmap' | 'hybrid' | 'satellite' | 'terrain' = 'roadmap';

  private _currentRoute$: Observable<Route>;
  private _currentRoute: Route;
  public currentWaypoint$: Observable<Waypoint>;
  private _currentWaypoint: Waypoint;

  private _currentLocation: google.maps.LatLng;
  private _currentLocationMarker: google.maps.Marker;
  private _map: google.maps.Map;

  constructor(private waypointRepository: WaypointRepositoryService) {
    this._currentRoute$ = waypointRepository.currentRoute$;

    this._currentRoute$.subscribe((route: Route) => {
      this._currentRoute = route;
    });

    this.currentWaypoint$ = waypointRepository.currentWaypoint$;

    this.currentWaypoint$.subscribe(waypoint => {
      this._currentWaypoint = waypoint;
    })
  }

  renderRoute(map: google.maps.Map): void {
    this._map = map;
    this.trackMe();

    this.initGoogleMaps();

    this.directionsRenderer.setMap(map);

    const waypoints = this._currentRoute.waypoints;

    this.directionsService.route(this.routeWaypoints(waypoints),
      (response: google.maps.DirectionsResult, status: google.maps.DirectionsStatus) => this.routeCalculated(waypoints, response, status, map));
  }

  private latLng(waypoint: Waypoint): google.maps.LatLng {
    return new google.maps.LatLng(waypoint.location.lat, waypoint.location.lng );
  }
  private googleWaypoint(waypoint: Waypoint): google.maps.DirectionsWaypoint {
    return { location: this.latLng(waypoint)};
  }


  private routeWaypoints(waypointList: Waypoint[]): google.maps.DirectionsRequest {
    let request: google.maps.DirectionsRequest = {
      travelMode: google.maps.TravelMode.DRIVING
    };

    const waypoints = Object.assign([], waypointList);

    if (waypoints.length >= 2) {
      const origin = this.latLng(waypoints[0]);
      const destination = this.latLng(waypoints[waypoints.length - 1]);

      const googleWaypoints: google.maps.DirectionsWaypoint[] = waypoints
        .map<google.maps.DirectionsWaypoint>((waypoint) => this.googleWaypoint(waypoint));

      request.origin = origin;
      request.destination = destination;
      request.waypoints = googleWaypoints;

    } else {
      // TODO: show only marker
    }

    return request;
  }

  private routeCalculated(waypointList: Waypoint[], response: google.maps.DirectionsResult,
                          status: google.maps.DirectionsStatus, map: google.maps.Map) {
    const waypoints = Object.assign([], waypointList);

    if (status === 'OK') {
      this.directionsRenderer.setDirections(response);

      if (exists(waypoints)) {
        const bounds = new google.maps.LatLngBounds();

        waypoints.forEach((waypoint, index) => {
          const marker = new google.maps.Marker({
            position: this.latLng(waypoint),
            map: map,
            title: '',
            icon: WAYPOINT_ICONS.appointmentIcons[index]
            //label: label
          });

          bounds.extend(marker.getPosition());

        });

        map.fitBounds(bounds);
      }

    } else {
      logWarn('Render route failed with status: ' + status);
    }
  }

  private findMe() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.showTrackingPosition(position);
      });
    } else {
      alert("Geolocation is not supported by this browser.");
    }
  };

  private trackMe() {
    if (navigator.geolocation) {
      navigator.geolocation.watchPosition((position) => {
        this.showTrackingPosition(position);
      });
    } else {
      alert("Geolocation is not supported by this browser.");
    }
  }

  private showTrackingPosition(position: Position) {
    console.log(`tracking postion:  ${position.coords.latitude} - ${position.coords.longitude}`);

    this._currentLocation = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);

    if (!this._currentLocationMarker) {
      this._currentLocationMarker = new google.maps.Marker({
        position: this._currentLocation,
        map: this._map,
        title: 'Hier bist du!',
        icon: WAYPOINT_ICONS.me
      });
    }
    else {
      this._currentLocationMarker.setPosition(this._currentLocation);
    }
  }

  toggleMapType(): void {
    if (this.type === 'hybrid') {
      this.type = 'roadmap';
    } else {
      this.type = 'hybrid';
    }
  }

  zoomIn(): void {
    this.options.zoom++;
  }

  zoomOut(): void {
    this.options.zoom--;
  }

  callContact(): void {
    window.open('tel:' + this._currentWaypoint.contact.phoneNumber, "_blank");
  }
  sendMail(): void {
    window.open('mailto:' + this._currentWaypoint.contact.email, "_blank");
  }
  navigateTo(): void {
    // TODO use Google Maps Intent window.open(`google.navigation:q=${this._currentWaypoint.location.lat},${this._currentWaypoint.location.lng}`, "_blank");
    window.open(`http://maps.google.com/maps?saddr=${this._currentLocation.lat()},${this._currentLocation.lng()}&daddr=${this._currentWaypoint.location.lat},${this._currentWaypoint.location.lng}`, "_blank");
  }

  get mapType(): 'roadmap' | 'hybrid' | 'satellite' | 'terrain' {
    return this.type;
  }

  get mapCenter(): LatLngLiteral {
    return this.centerLatLng;
  }

  get mapBoundary(): LatLngBoundsLiteral {
    return this.boundary;
  }

  get mapOptions() {
    return this.options;
  }

  get mapZoom(): number {
    return this.options.zoom;
  }
  set mapZoom(zoom: number) {
    this.options.zoom = zoom;
  }

  private placeMarker(location: google.maps.LatLng, map: google.maps.Map): void {

  }


  private initGoogleMaps(): void {
    this.directionsService = new google.maps.DirectionsService();
    this.directionsRenderer = new google.maps.DirectionsRenderer({
      suppressMarkers: true,
      preserveViewport: true,
      hideRouteList: true,
    });
  }
}
