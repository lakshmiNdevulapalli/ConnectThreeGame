pipeline {
  agent any
  stages {
    stage('Lint & Unit Test') {
      parallel {
        stage('checkStyle') {
          steps {
            sh 'chmod +x gradlew'
            sh './gradlew clean'
            sh './gradlew assembleDebug'
          }
        }
        stage('Lint Test') {
          steps {
            sh './gradlew lint'
          }
        }
        stage('Unit Test') {
          steps {
            sh './gradlew testStagingDebug'
          }
        }
      }
    }
    stage('UI Testing') {
      steps {
        script {
          if(currentBuild.result == null || currentBuild.result == 'SUCCESS'){
            //Start your emulator, testing tools
            sh 'appium &'
            sh 'rspec spec -fd'
          }
        }

      }
    }
  }
}