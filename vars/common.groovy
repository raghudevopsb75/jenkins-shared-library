def compileCode(appType) {
  stage('Download Dependencies') {
    if (appType == "nodejs") {
      sh 'npm install'
    }
  }
}

