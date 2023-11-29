def call() {
  node('workstation') {
    common.codeCheckout()
    common.compileCode()
    stage('Code Quality') {
      sh 'echo OK'
    }
    stage('Unit Tests') {
      sh 'echo OK'
    }

  }
}
