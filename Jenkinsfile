pipeline{
    agent any
    stages{
        stage('Build Backend') {
            steps {
                bat 'mvn clean package -DskipTests=true'
            }
        }
        stage('Unit Tests') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Deploy Backend') {
            steps {
                deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
            }
        }
        stage('API Tests') {
            steps {
            	dir('api-test') {
					git 'https://github.com/victorberton/tasks-api-test'
					bat 'mvn test'
				}
            }
        }
        stage('Deploy Frontend') {
            steps {
	            dir('frontend') {
		            git 'https://github.com/victorberton/tasks-frontend'
		            bat 'mvn clean package'
	                deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks', war: 'target/tasks.war'
	            }
            }
        }
        stage('Functional Tests') {
            steps {
            	dir('functional-test') {
					git 'https://github.com/victorberton/tasks-functional-tests'
					bat 'mvn test'
				}
            }
        }
        stage('Deploy Prod') {
            steps {
            	bat 'docker-compose build'
            	bat 'docker-compose up -d'
            }
        }
        stage('Health Check') {
            steps {
                sleep(5)
            	dir('functional-test') {
					bat 'mvn verify -Dskip.surefire.tests'
				}
            }
        }
    }
}