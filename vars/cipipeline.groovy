def call() {
  node('workstation') {
    common.compileCode()
  }
}
