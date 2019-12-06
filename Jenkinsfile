node{
   stage('SCM Checkout'){
        checkout scm
   }
    stage('Mvn Package'){
        def mvnHome = tool name: 'maven-3', type: 'maven'
        def mvnCMD = "${mvnHome}/bin/mvn"
        sh "${mvnCMD} clean package"
   }
   stage('Deploy to tomcat'){
        deploy adapters: [tomcat8(credentialsId: 'tomcat-account', path: '', 
        url: 'http://172.16.0.2:8181')], 
        contextPath: '/LiquorStore', 
        war: '**/*.war'
   }
    stage("Trigger Silk Central Executions") {
        def path = tool name: 'gradle5.6.4', type: 'gradle'
        def scFile = new File(pwd(), "silkcentral.gradle")
        scFile.delete()
        scFile.getParentFile().mkdirs()
        writeFile([file: scFile.getAbsolutePath(), text: new URL ("http://172.16.0.1:19120/silkroot/tools/silkcentral.gradle").getText()])
        def scTriggerInfo = '-Psc_executionNodeIds=45 -Psc_host=http://172.16.0.1:19120 -Psc_token=0f58a614-edcd-403c-8953-fdeb37a572ca'
        if (isUnix()) { 
            sh "${path}/bin/gradle :silkCentralLaunch -b ${scFile} " + scTriggerInfo
        } else {
            bat "${path}/bin/gradle.bat :silkCentralLaunch -b ${scFile} " + scTriggerInfo
        }
    junit 'sc_results/junit*.xml' 
  }
}