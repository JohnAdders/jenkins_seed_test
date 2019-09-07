def gitUrl = "https://github.com/JohnAdders/jenkinsfile_test.git"

multibranchPipelineJob('Jenkinsfile.test.project') {
    branchSources {
        git {
            id('123456789') // IMPORTANT: use a constant and unique identifier
            remote(gitUrl)
        }
    }
}
