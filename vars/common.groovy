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

    sh "find . | sed -e '1d' |xargs rm -rf"
    if(env.TAG_NAME ==~ ".*") {
      env.branch_name = "refs/tags/${env.TAG_NAME}"
    } else {
      env.branch_name = "${env.BRANCH_NAME}"
    }
    checkout scmGit(
        branches: [[name: branch_name]],
        userRemoteConfigs: [[url: "https://github.com/raghudevopsb75/${component}"]]
    )
  }
}

def appRelease() {
  sh 'echo OK'
}
