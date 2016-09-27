#!/bin/bash

if [ "$TRAVIS_REPO_SLUG" == "Fastjur/SEM-Project" ] && [ "$TRAVIS_JDK_VERSION" == "oraclejdk8" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

  echo -e "Publishing javadoc...\n"

  cp -R target/site $HOME/site-latest

  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"
  git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/Fastjur/SEM-Project gh-pages > /dev/null

  cd gh-pages
  git rebase master -f
  git rm -rf ./docs/site
  cp -Rf $HOME/site-latest ./docs/site
  git add -f .
  git commit -m "Latest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages"
  git push -fq origin gh-pages > /dev/null

  echo -e "Published Javadoc to gh-pages.\n"

fi
