node {
	stage("Clone the project"){
		git branch: 'main', url : 'https://github.com/priyaAnusha/e-book-backend-update.git'
	}
	
	stage("Compilation"){
		bat "./mvnw clean install -DskipTests"
	}
	
	stage("Tests and run"){
		stage("Running unit tests"){
			bat "./mvnw test -Punit"
		}
		stage("Run the application"){
			bat 'java -jar target/ propertyFinder-0.0.1-SNAPSHOT.jar'
		}
	}
}