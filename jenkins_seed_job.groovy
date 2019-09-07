// define a Job setting class
class JobSetting {
    String UniqueID;
    String GitUrl;
    String JobName;
	
    JobSetting(String UniqueID, String GitUrl, String JobName) {
        this.UniqueID = UniqueID
        this.GitUrl = GitUrl
        this.JobName = JobName
    }
}

// Create a list of job settings
def job_settings = [
// Add any new jobs following the pattern
    new JobSetting('123456789', "https://github.com/JohnAdders/jenkinsfile_test.git", 'Jenkinsfile.test.project'),
    new JobSetting('310370001', "https://github.com/JohnAdders/jenkinsfile_test.git", 'Jenkinsfile.test.project2'),
]


// create a Jenkins job multipipe job for each one
// this uses the default setting which uses Jenkinsfile to control the pipeline
for (job_setting in job_settings) {
    multibranchPipelineJob(job_setting.JobName) {
        branchSources {
            git {
                id(job_setting.UniqueID) // IMPORTANT: use a constant and unique identifier
                remote(job_setting.GitUrl)
            }
        }
    }
}

