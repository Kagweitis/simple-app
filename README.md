# Simple App Deployment Guide

## Table of Contents
1. [Build and Run Application Locally Using Docker](#build-and-run-application-locally-using-docker)
2. [Deploy Application on Minikube](#deploy-application-on-minikube)
    - [Minikube Setup Commands](#minikube-setup-commands)
    - [Accessing the Application](#accessing-the-application)
3. [CI/CD Pipeline Explanation](#cicd-pipeline-explanation)
4. [Decisions, Assumptions, and Challenges Faced](#decisions-assumptions-and-challenges-faced)

---

## Build and Run Application Locally Using Docker

### Prerequisites
- Docker installed on your local machine.
- Access to Docker Hub or an image repository.

### Steps
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/<your-repo>/simple-app.git
   cd simple-app
   ```

2. **Build and Run the Application Locally Using Docker**:
    ```bash
   docker build -t simple-app:latest .
   docker run -d -p 5050:5050 simple-app:latest
    ```
    Once running, open the browser and access the application via
    http://localhost:5050/api/v1/books
    it should return
    ```json
    {
    "id": 1,
    "name": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "rating": 4.5
    }
    ```
3. **Steps to deploy the application on Minikube**
- Ensure you have minikube installed
- Run the following command to start minikube
    ```bash
        minikube start
     ```
- Once running cd into the manifests directory and deploy on minikube
    ```bash
    cd k8s
    kubectl apply -f deployment.yaml
    ```
- Expose the Deployment and retrieve the service url
    ```bash
    kubectl expose deployment simple-app-deployment --type=NodePort --port=5050
    minikube service simple-app-deployment --url
    ```
- Access the application using the url via browser or postman
