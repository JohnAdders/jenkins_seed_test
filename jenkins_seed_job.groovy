def gitUrl = "https://github.com/JohnAdders/jenkinsfile_test.git"

job("Jenkinsfile.test.project") {
    description "Builds Project from master branch."
    scm {
        git {
            remote {
                url gitUrl
                branch "origin/master"
            }
        }
    }
    steps {
        shell "echo Look: I'm building master!"
    }
}
