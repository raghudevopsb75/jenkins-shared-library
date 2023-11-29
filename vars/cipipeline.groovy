def call() {
  node('workstation') {
    common.codeCheckout()
    common.compileCode()

    if(env.TAG_NAME ==~ ".*") {
      common.appRelease()
    } else {
      stage('Code Quality') {
        sh 'echo OK'
      }
      stage('Unit Tests') {
        sh 'echo OK'
      }
    }
  }
}
