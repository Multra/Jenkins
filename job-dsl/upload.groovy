job('upload example') {
  scm {
    git('git://github.com/Multra/docker-hello.git') { node ->
      node / gitConfigName('Multra')
      node / gitConfigEmail('Multra@gmail.com')
    }
  }
  triggers {
    scm('H/5 * * * *')
  }
  wrappers {
    golang('golang')
  }
  steps {
    dockerBuildAndPublish {
      repositoryName('multra/docker-hello')
      tag('${GIT_REVISION,length=9}')
      registryCredentials('dockerhub')
      forcePull(false)
      forceTag(false)
      createFingerprints(false)
      skipDecorate()
    }
  }
  
}
