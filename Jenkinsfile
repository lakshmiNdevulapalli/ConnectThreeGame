pipeline {
    agent any
    stages{
        stage('Lint & Unit Test'){
            parallel{
                stage('checkStyle'){
                    steps{
                        sh 'chmod +x gradlew'
                        sh './gradlew clean'
                        sh './gradlew assembleDebug'
                    }
                }
                stage('Unit Test'){
                    steps{
                        //Execute you unit test
                        sh './gradlew lint'
                    }
                }
            }
        }
        stage('UI Testing'){
            steps {
                script {
                    if(currentBuild.result == null || currentBuild.result == 'SUCCESS'){
                        //Start your emulator, testing tools
                        sh 'emulator @Nexus_Emulator_API_24'
                        sh 'appium &'
                        sh 'rspec spec -fd'
                    }
                }
            }
        }
    }
}