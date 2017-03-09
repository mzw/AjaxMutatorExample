# How to Use AjaxMutator

Written by [Yuta Maezawa](maezawa@nii.ac.jp).

## `quizzy.tar.gz`

[Quizzy](http://quizzy.sourceforge.net/) is an OSS Quiz library. We use Quizzy as an example Ajax Web applications to demonstrate AjaxMutator.

## `localenv.properties`

Describe app-independent information to run AjaxMutator.

1. A path to a Web browser binary. AjaxMutator allows to use Firefox or PhantomJS.
  - `firefox-bin`
  - `phantomjs-bin`
2. A port number that a proxy server listens (e.g., `8080` ).


## `quizzy.properties`

Describe app-dependent information to run AjaxMutator.

1. A path to directory where AjaxMutator records source code files of an Ajax Web application under test.
2. URL where the application is deployed.
3. A relative path to a JavaScript file to which our mutation operators are applied.
4. A proxy server mode
  - `ram` : Just instantiate a proxy server.
  - `ram record` : Store source code files of the application into a local file system.
  - `ram rewrite` : Apply our mutation operators to the files.

## `QuizzyTest.java`

Describe test cases. In addition, this code contains our utility codes supporting to run AjaxMutator.

## `QuizzyConfig.java`

Contains a `main` method to invoke mutation testing with AjaxMutator.
