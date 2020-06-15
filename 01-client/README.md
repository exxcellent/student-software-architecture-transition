# Software Architecture Transition Referenz Projekt - Client

Der Client ist eine Angular SPA.

```
$ npm install

$ npm run start
```

Öffne `http://localhost:4200` mit einem modernen Browser. Das Projekt nutzt
[CSS Variables](https://caniuse.com/#search=css%20variables) und der Internet Explorer unterstützt diese Funktion nicht.

## Struktur

Eine Web-Applikation besteht aus fachlichen Modulen (navigation, ...), 
die jeweils einen Anwendungsfalls beinhalten und 
technischen Querschnittsmodulen (shared, ...).

* Ein fachliches Modul hat als Einstiegspunkte `pages`. Bei SPA entspricht dies einer dynamisch 
eingebetteten "Content"-Seite über das Router-Outlet.
* Eine `page` besteht aus `dialogs`, die die fachlichen Funktionen bereitstellen.
* Ein `dialog` nutzt interne spezifische `ui-components` oder 
wiederverwendbare `ui-components` aus dem eigenen Modul oder die `shared-ui-components`
* Ein `dialog` besitzt neben der `ui-components` (Präsentation) auch einen `dialogcore` für die Geschäftslogik.
* Innerhalb eines fachlichen Moduls befinden sich in `models` die fachlichen Entitäten.
* Unter `data-access` befinden sich Services zur Bereitstellung von Server-Daten. Die `data-provider` können 
innerhalb des Moduls wiederverwendet werden. `mapper` konvertieren die Transfer-Objekte aus `types` 
in Model-Objekte unter `models`.

```
app
├ modules
│  ├ navigation                             <-- fachliches Modul
│  │ ├ pages
│  │ │ ├ navigation                         <-- Einstiegspunkt aus Präsentationssicht
│  │ │ │ ├ navigation.page.ts                   enthält Dialoge und UI-Komponenten
│  │ ├ dialogs
│  │ │ ├ navigation-map
│  │ │ │ ├ ui-components
│  │ │ │ ├ guards
│  │ │ │ ├ navigation-map.component.ts
│  │ │ │ ├ navigation-map.component.html
│  │ │ │ ├ navigation-map.component.css
│  │ │ │ ├ navigation-map.dialogcore.ts
│  │ ├ ui-components
│  │ │ ├ navigation-map-control-bar
│  │ ├ model
│  │ │ ├ route.ts
│  │ │ ├ waypoint.ts
│  │ ├ data-access                          <-- Datenzugriff
│  │ │ ├ types
│  │ │ │ ├ route.to.ts
│  │ │ │ ├ wayoint.to.ts
│  │ │ ├ mapper
│  │ │ │ ├ route.mapper.ts
│  │ │ ├ routes.data-provider.ts
│  │ │ ├ waypoints.data-provider.ts
│  │ ├ guards
│  ├ shared-ui-components                   <-- technisches Modul
│  │ ├ animations
│  │ ├ form-controls
│  │ ├ icons
│  │ ├ layout
│  │ ├ media
│  │ ├ theme
│  │ └ typography
│  ├ shared                                 <-- technisches Modul
│  │ ├ functions
│  │ ├ pipes
│  │ ├ services
│  │ └ types
│  └ store                                  <-- technisches Modul
│    └ navigation
│      └ navigation.store.ts
├ index.html                                <-- Einstiegspunkt für die Web-App
└ styles.css
```

### step-1/strict-layer-architecture

Die Applikation wurde in ihre Schichten geteilt. 
Die ehemalige route-planning App enthält nur noch die Präsentationsschicht und 
den Angular Grundaufbau.

Die Dialogkern- und Datenzugriffsschicht sind in sog. Libraries ausgelagert und 
können gesondert per NPM bereitgestellt werden.

Die Querschnittsthemen wurden in shared-Code ausgelagert.

Siehe https://nx.dev/angular/tutorial/08-create-libs und https://angular.io/guide/libraries

_Hinweis: Die Domäne "Navigation" war bereits in dem Referenzprojekt sichtbar._ 

```
apps
├ route-planning                                 <-- Anwendung mit Querschnittsfunktionen und der Präsentationsschicht
│ ├ src
│ │ ├ app
│ │ │ ├ modules
│ │ │ │ ├ navigation                             <-- Domäne/ fachliches Modul
│ │ │ │ │ ├ pages
│ │ │ │ │ ├ navigation                           <-- Einstiegspunkt aus Präsentationssicht
│ │ │ │ │ │ └ navigation.page.ts                     enthält Dialoge und UI-Komponenten
│ │ │ │ │ ├ dialogs
│ │ │ │ │ │ ├ navigation-day-selector            <-- in den Dialogen nur die Präsentationsschicht
│ │ │ │ │ │ │ ├ navigation-day-selector.component.css
│ │ │ │ │ │ │ ├ navigation-day-selector.component.html
│ │ │ │ │ │ │ └ navigation-day-selector.component.ts
│ │ │ │ │ │ ├ navigation-map
│ │ │ │ │ │ ├ navigation-waypoint-details
│ │ │ │ │ │ ├ navigation-waypoints
│ │ │ │ │ └ ui-components
libs
├ dialog-core
│ ├ src
│ │ ├ lib
│ │ │ ├ modules
│ │ │ │ ├ navigation                             <-- Dialogkernschicht der Navigation Domäne
├ data-access
│ ├ src
│ │ ├ lib
│ │ │ ├ modules
│ │ │ │ ├ navigation                             <-- Datenzugriffsschicht der Navigation Domäne
├ model
│ ├ src
│ │ ├ lib
│ │ │ ├ modules
│ │ │ │ ├ navigation                             <-- geteiltes Domänenmodell der Navigation
├ shared                                         <-- Querschnittscode
├ shared-ui-components
```

#### Validierung der Schichtenarchitektur

Nx unterstützt ein Tagging der Apps und Libraries, 
sodass per TSLint die Aufrufherarchie der Schichten sichergestellt werden kann:

```
nx.json

...
"projects": {
    "route-planning": {
      "tags": ["application"]     <-- Definition der Schichten-Tags
    },
    "shared": {
      "tags": ["common"]
    },
    "shared-ui-components": {
      "tags": ["common"]
    },
    "dialog-core": {
      "tags": ["dialog-core"]
    },
    "data-access": {
      "tags": ["data-access"]
    },
    "model": {
      "tags": ["model"]
    }
  }


tslint.json

...
"nx-enforce-module-boundaries": [
      true,
      {
        "enforceBuildableLibDependency": true,
        "allow": [],
        "depConstraints": [
          {
            "sourceTag": "application",
            "onlyDependOnLibsWithTags": ["dialog-core", "model", "common", "application"]
          },
          {
            "sourceTag": "dialog-core",
            "onlyDependOnLibsWithTags": ["data-access", "model", "common", "dialog-core"]
          },
          {
            "sourceTag": "data-access",
            "onlyDependOnLibsWithTags": ["model", "common", "data-access"]
          },
          {
            "sourceTag": "model",
            "onlyDependOnLibsWithTags": ["common"]
          },
          {
            "sourceTag": "common",
            "onlyDependOnLibsWithTags": ["common"]
          }
        ]
      }
    ],
```


## Setup

Das Client-Projekt wurde mit [Nx](https://nx.dev) generiert. 

> Nx is a set of Extensible Dev Tools for Monorepos.

[Nx Documentation](https://nx.dev/angular)

[10-minute video showing all Nx features](https://nx.dev/angular/getting-started/what-is-nx)

[Interactive Tutorial](https://nx.dev/angular/tutorial/01-create-application)

### Monorepo/ Workspace anlegen

```
$ npx create-nx-workspace@latest software-architecture-transition
? What to create in the new workspace   empty        [an empty workspace]
? CLI to power the Nx workspace         Angular CLI  [Extensible CLI for Angular applications. Recommended for Angular projects.]
```

### Angular-Applikation anlegen

```
$ ng add @nrwl/angular
? Which Unit Test Runner would you like to use for the application?     Jest        [ https://jestjs.io ]
? Which E2E Test Runner would you like to use?                          Cypress     [ https://www.cypress.io ]

$ ng g @nrwl/angular:app route-planning --prefix r --skip-tests --style css --inline-style --inline-template --routing --enable-ivy --defaults
```

_Hinweis: Die E2E-Tests befinden sich im Branch `feature/e2e-tests`_

### Entwicklungsmodus starten

Über ```$ npm run start``` startet die Entwicklungsumgebung. Unter `http://localhost:4200` befindet sich die Anwendung.

```$ ng serve -o```

> Achtung: Unter `/01-client/software-architecture-transition/apps/route-planning/src/environments/` 
> muss eine `environment.local.ts` Datei mit dem GoogleMaps API Key angelegt werden oder 
> `environment.ts` geändert werden.

### Dialog-Komponente generieren

```
$ ng g component navigation-map -p nav -t false -s false
CREATE .../navigation-map/navigation-map.component.css
CREATE .../navigation-map/navigation-map.component.html
CREATE .../navigation-map/navigation-map.component.ts 

$ cd navigation-map

$ ng g service navigation-map       
CREATE .../navigation-map.service.ts (142 bytes)

$ mv navigation-map.service.ts navigation-map.dialogcore.ts
```


### NgRx Store Feature generieren

```
$ ng g @ngrx/schematics:feature state/app/app -m app.module.ts --creators --api --skip-test
$ ng g @ngrx/schematics:feature modules/navigation/data-access/waypoint/state/route/route -m modules/navigation/navigation.module.ts --creators --api --skip-test
$ ng g @ngrx/schematics:feature modules/user/data-access/authentication/state/user-credential/user-credential -m modules/user/user.module.ts --creators --api --skip-test
```


### Probleme mit GoogleMaps Node Types

Für die Google Maps JavaScript API gibt es `@types/googlemaps`, 
damit die JavaScript API auch in Angular mit TypeScript 
genutzt werden kann. 

> Der TypeScript Compiler und 
manche IDEs erkennen die Typen aber nicht.

```
npm install typings --global
typings install dt~google.maps --global --save
```