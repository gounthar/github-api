# Java API for GitHub

[![Sonatype Nexus (Releases)](https://img.shields.io/nexus/r/org.kohsuke/github-api?server=https%3A%2F%2Foss.sonatype.org)](https://mvnrepository.com/artifact/org.kohsuke/github-api)
[![Join the chat at https://gitter.im/hub4j/github-api](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/hub4j/github-api?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
![CI](https://github.com/hub4j/github-api/workflows/CI/badge.svg?branch=main)
[![codecov](https://codecov.io/gh/hub4j/github-api/branch/main/graph/badge.svg?token=j1jQqydZLJ)](https://codecov.io/gh/hub4j/github-api)


See https://github-api.kohsuke.org/ for more details

## Forking a Repository

To fork a repository and retrieve only the default branch, you can use the `forkRepository` method in the `GitHub` class. Here's an example:

```java
GitHub github = new GitHubBuilder().withOAuthToken("YOUR_OAUTH_TOKEN").build();
GHRepository forkedRepo = github.forkRepository("owner", "repository");
System.out.println("Forked repository: " + forkedRepo.getFullName());
```
