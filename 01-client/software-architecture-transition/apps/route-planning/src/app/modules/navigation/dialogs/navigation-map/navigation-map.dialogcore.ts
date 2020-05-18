import {Injectable} from '@angular/core';
import {exists, log, logWarn} from '../../../shared/functions';
// @ts-ignore
import {LatLngBoundsLiteral, LatLngLiteral} from '@agm/core';
import {Waypoint} from '../../model/waypoint';
import {WaypointRepositoryService} from '../../data-access/waypoint/waypoint-repository.service';

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

  private iconUrls = [
    'https://firebasestorage.googleapis.com/v0/b/swm-onpo-routenplaner-deploy.appspot.com/o/icon-A-32.png?alt=media&token=6ed6aca2-545f-4a43-b49b-6b8197b9c06b',
    'https://firebasestorage.googleapis.com/v0/b/swm-onpo-routenplaner-deploy.appspot.com/o/icon-B-32.png?alt=media&token=989ad504-aba6-48fd-80bb-56a1b50ba686',
    'https://firebasestorage.googleapis.com/v0/b/swm-onpo-routenplaner-deploy.appspot.com/o/icon-C-32.png?alt=media&token=660471bf-bb43-4389-936d-ba8375f7b346',
    'https://firebasestorage.googleapis.com/v0/b/swm-onpo-routenplaner-deploy.appspot.com/o/icon-D-32.png?alt=media&token=5179f3a2-beb9-457d-8734-cb50139652c8',
    'https://firebasestorage.googleapis.com/v0/b/swm-onpo-routenplaner-deploy.appspot.com/o/icon-E-32.png?alt=media&token=4da72085-31d8-4a46-8ffb-2dd46ec41d67',
    'https://firebasestorage.googleapis.com/v0/b/swm-onpo-routenplaner-deploy.appspot.com/o/icon-F-32.png?alt=media&token=5bcc7e7c-821a-475c-9783-e84641a8398b',
    'https://firebasestorage.googleapis.com/v0/b/swm-onpo-routenplaner-deploy.appspot.com/o/icon-G-32.png?alt=media&token=27988efa-a39d-4532-932b-52d2b39b397a',
    'https://firebasestorage.googleapis.com/v0/b/swm-onpo-routenplaner-deploy.appspot.com/o/icon-H-32.png?alt=media&token=8d492946-36cb-4f67-93f0-2d78f577df00',
    'https://firebasestorage.googleapis.com/v0/b/swm-onpo-routenplaner-deploy.appspot.com/o/icon-I-32.png?alt=media&token=b744f696-e0c2-47aa-ab0d-4a25330e741a',
    'https://firebasestorage.googleapis.com/v0/b/swm-onpo-routenplaner-deploy.appspot.com/o/icon-J-32.png?alt=media&token=21be1fe5-c7f9-4e35-839e-0c8305cdbdc3',
  ];

  constructor(private waypointRepository: WaypointRepositoryService) {
  }

  renderRoute(map: google.maps.Map): void {
    this.initGoogleMaps();

    this.directionsRenderer.setMap(map);

    //const waypoints = this.requestCurrentWaypoints();
    // log('Waypoints: ' + JSON.stringify(waypoints));
    //
    // this.directionsService.route(this.routeWaypoints(waypoints),
    //   (response: google.maps.DirectionsResult, status: google.maps.DirectionsStatus) => this.routeCalculated(waypoints, response, status, map));
  }

  private latLng(waypoint: Waypoint): google.maps.LatLng {
    return new google.maps.LatLng(waypoint.location.lat, waypoint.location.lng );
  }
  private googleWaypoint(waypoint: Waypoint): google.maps.DirectionsWaypoint {
    return { location: this.latLng(waypoint), stopover: true };
  }


  private routeWaypoints(waypointList: Waypoint[]): google.maps.DirectionsRequest {
    let request: google.maps.DirectionsRequest = {
      travelMode: google.maps.TravelMode.DRIVING
    };

    const waypoints = Object.assign([], waypointList);

    if (waypoints.length === 2) {
      request.origin = this.latLng(waypoints[0]);
      request.destination = this.latLng(waypoints[1])

    } else if (waypoints.length > 2) {
      const origin = this.latLng(waypoints[0]);
      const destination = this.latLng(waypoints[waypoints.length - 1]);

      let intermediateWaypoints = waypoints.splice(0);
      intermediateWaypoints = intermediateWaypoints.splice(intermediateWaypoints.length - 1);

      const googleWaypoints: google.maps.DirectionsWaypoint[] = intermediateWaypoints
        .map<google.maps.DirectionsWaypoint>((waypoint) => this.googleWaypoint(waypoint));

      request.origin = origin;
      request.destination = destination;
      request.waypoints = googleWaypoints;
    } else {
      // TODO: show only marker
    }

    return request;
  }

  private requestCurrentWaypoints(): Waypoint[] {

    //this.waypointRepository.findDailyWaypoints(new Date(2020, 4, 10), 1);
return null;
    // return [
    //   { location: { lat: 48.12738575287784, lng: 11.416353270520176} },
    //   { location: { lat: 48.219012, lng: 11.624689} },
    //   { location: { lat: 48.250615, lng: 11.6553616} },
    // ];
  }


  private routeCalculated(waypointList: Waypoint[], response: google.maps.DirectionsResult,
                          status: google.maps.DirectionsStatus, map: google.maps.Map) {
    const waypoints = Object.assign([], waypointList);

    if (status === 'OK') {
      log('Render route directions');
      this.directionsRenderer.setDirections(response);

      log('Waypoints: ' + JSON.stringify(waypoints));

      if (exists(waypoints)) {
        waypoints.forEach((waypoint, index) => {
          new google.maps.Marker({
            position: this.latLng(waypoint),
            map: map,
            title: '',
            icon: this.iconUrls[index]
            //label: label
          });
        })
      }

    } else {
      logWarn('Render route failed with status: ' + status);
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
