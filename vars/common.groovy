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
  stage('CodeCheckout') {
    sh 'env'
    sh 'find . | sed 1d | xargs rm -rf'
    git branch: 'main', url: 'https://github.com/raghudevopsb75/shipping'
  }
}

