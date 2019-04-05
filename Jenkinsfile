pipeline {
    agent any
    stgaes{
        stage('Lint & Unit Test'){
            parallel{
                stage('checkStyle'){
                    steps{
                        sh './gradlew checkStyle'
                    }
                }
                stage('Unit Test'){
                    steps{
                        //Execute you unit test
                        sh './gradlew testStagingDebug'
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