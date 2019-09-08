// import required for md5 hash
import java.security.MessageDigest


// define a Job setting class
class JobSetting {
    String UniqueID;
    String GitUrl;
    String JobName;
	
    JobSetting(String GitProject, String JobName) {
        this.UniqueID = CalcMD5(JobName)
        this.GitUrl = "https://github.com/" + GitProject
        this.JobName = JobName
    }

    def CalcMD5(String s) {
        MessageDigest.getInstance("MD5").digest(s.bytes).encodeHex().toString()
    }
}

// Create a list of job settings
def job_settings = [
// Add any new jobs following the pattern
    new JobSetting("JohnAdders/jenkinsfile_test.git", 'Jenkinsfile.test.project'),
    new JobSetting("JohnAdders/jenkinsfile_test.git", 'Jenkinsfile.test.project2'),
]


// create a Jenkins job multipipe job for each one
// this uses the default setting which uses Jenkinsfile to control the pipeline
// and keeps old branch builds around for a month
for (job_setting in job_settings) {
    multibranchPipelineJob(job_setting.JobName) {
        branchSources {
            git {
                id(job_setting.UniqueID)
                remote(job_setting.GitUrl)
            }
        }
        orphanedItemStrategy {
            discardOldItems {
                daysToKeep(31)
            }
        }    
    }
}

