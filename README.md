# factoryimagechecker

Small Android app aimed at Nexus Warriors (Nexus 6, Nexus 6P and Nexus 9 LTE) who are still waiting for the official Nougat Factory Images to drop for their phones.

The app uses GCMNetworkManager to schedule a service which checks for the existence of the Factory Images.

The app is compiled using the Jack toolchain, which is used to backport Java 8 features (lambdas are used in this app).
