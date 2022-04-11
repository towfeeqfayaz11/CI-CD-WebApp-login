node {
    stage('checkout') {

       git branch: 'master', credentialsId: 'git-cred', url: 'https://github.com/towfeeqfayaz11/CI-CD-WebApp-login'
    }
    stage('Build and run java') {
    //   withAnt(installation: 'ant9', jdk: 'java8') {
    //       bat 'ant -f build.xml deploy'    //here default target is deploy in build.xml
    //   }
       withAnt(installation: 'ant9', jdk: 'java8') {
            if (isUnix()) {
                sh 'ant'         // it will by default look for build.xml in home directory and pick default target
            } else {
                bat('ant')      // it will by default look for build.xml in home directory and pick default target
            }
        }
    }
    stage('Results') {
        echo 'webapp started successfully'
    }
}
