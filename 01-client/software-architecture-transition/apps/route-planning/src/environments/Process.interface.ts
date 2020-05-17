declare var process: Process;

interface Process {
  env: Env
}

interface Env {
  GOOGLE_MAPS_API_KEY: string
}

interface GlobalEnvironment {
  process: Process
}
