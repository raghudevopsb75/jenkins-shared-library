def compileCode(appType) {
  stage('Download Dependencies') {
    if (appType == "nodejs") {
      sh 'npm install'
    }
    if (appType == "java") {
      sh 'mvn clean package'
    }
  }
}

def codeCheckout() {
  git branch: 'main', url: 'https://github.com/raghudevopsb75/shipping'
  sh 'find . | sed 1d'
}
