# Software Architecture Transition Referenz Projekt - Client

Der Client ist eine Angular SPA.

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

```$ ng serve -o```