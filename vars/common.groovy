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
    print "branch_name - ${branch_name}"
    checkout scmGit(
        branches: [[name: branch_name]],
        userRemoteConfigs: [[url: "https://github.com/raghudevopsb75/${component}"]]
    )
  }
}

//def appRelease(appType) {
//  sh 'echo ${TAG_NAME} >VERSION'
//  stage('Release App Version') {
//    if (appType == "nodejs") {
//      sh 'zip -r ${component}-${TAG_NAME}.zip *'
//    }
//    if (appType == "java") {
//      sh 'mv target/${component}-1.0.jar ${component}.jar'
//      sh 'zip -r ${component}-${TAG_NAME}.zip ${component}.jar VERSION schema'
//    }
//    if (appType == "python") {
//      sh 'zip -r ${component}-${TAG_NAME}.zip *'
//    }
//    if (appType == "nginx") {
//      sh 'zip -r ${component}-${TAG_NAME}.zip *'
//    }
//    sh 'curl -sSf -u "admin:Admin123" -X PUT -T ${component}-${TAG_NAME}.zip "http://artifactory.rdevopsb73.online:8081/artifactory/${component}/${component}-${TAG_NAME}.zip"'
//  }
//}

def appRelease(appType) {
  stage('Docker Build') {
    sh 'docker build -t 739561048503.dkr.ecr.us-east-1.amazonaws.com/${component}:${TAG_NAME} .'
    sh 'aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 739561048503.dkr.ecr.us-east-1.amazonaws.com'
    sh 'docker push 739561048503.dkr.ecr.us-east-1.amazonaws.com/${component}:${TAG_NAME}'
  }
}
