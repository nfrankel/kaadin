#!/bin/bash
# https://gist.github.com/domenic/ec8b0fc8ab45f39403dd
set -e # Exit with nonzero exit code if anything fails

SOURCE_BRANCH="master"
TARGET_BRANCH="gh-pages"

# Pull requests and commits to other branches shouldn't try to deploy, just build to verify
if [ "$TRAVIS_PULL_REQUEST" != "false" -o "$TRAVIS_BRANCH" != "$SOURCE_BRANCH" ]; then
    echo "Skipping deploy; just doing a build."
    exit 0
fi

# Save some useful information
REPO=`git config remote.origin.url`
SSH_REPO=${REPO/https:\/\/github.com\//git@github.com:}
SHA=`git rev-parse --verify HEAD`

git checkout $TARGET_BRANCH || git checkout --orphan $TARGET_BRANCH

# Now let's go have some fun with the cloned repo
git config user.name "$COMMIT_AUTHOR_NAME"
git config user.email "$COMMIT_AUTHOR_EMAIL"

mv target/generated-docs/* .

# If there are no changes to the compiled out (e.g. this is a README update) then just bail.
if ! [[ `git status --porcelain` ]]; then
    echo "No changes to the output on this push; exiting."
    exit 0
fi

# Commit the "changes", i.e. the new version.
# The delta will show diffs between new and old versions.
git add *.html
git commit -m "Deploy to GitHub Pages: ${SHA}"

git remote add pages https://nfrankel:${GH_PAGE}@github.com/nfrankel/kaadin.git

# Now that we're all set up, we can push.
git push pages HEAD --force