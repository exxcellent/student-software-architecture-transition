# Software Architecture Transition Referenz Projekt - Client

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

$ 
```