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
## Deploy Application on Minikube
### Minikube Setup Commands
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
### Accessing the Application
- To make the application accessible, run the commands below
    ```bash
    kubectl expose deployment simple-app-deployment --type=NodePort --port=5050
    minikube service simple-app-deployment --url
    ```
- Access the application using the url via browser or postman
## CI/CD Pipeline Explanation

The CI/CD pipeline in GitHub Actions automates the process of building, testing, and deploying the application, ensuring that new code changes are continuously integrated and deployed in a seamless manner.

### Overview
The pipeline is triggered on any push or pull request to the `main` branch. It builds the Docker image, tests it, and then pushes it to Docker Hub. If necessary, the pipeline can also be extended to deploy the application to a Kubernetes environment.

### Workflow Steps

1. **Login to Docker Hub**:
    - The action logs into Docker Hub using credentials stored in GitHub Secrets (`DOCKERHUB_USERNAME` and `DOCKERHUB_TOKEN`).
    - This step ensures that the workflow has the necessary permissions to push the built image to Docker Hub.
   ```yaml
   - name: Login to Docker Hub
     uses: docker/login-action@v3
     with:
       username: ${{ secrets.DOCKERHUB_USERNAME }}
       password: ${{ secrets.DOCKERHUB_TOKEN }}
2. **Set up QEMU**

    The `docker/setup-qemu-action` is used to enable cross-platform builds. This is useful when building Docker images for multiple architectures (e.g., x86_64, ARM). By setting up QEMU, we can build images for different platforms even on a single architecture runner.

3. **Set up Docker Buildx**

    The `docker/setup-buildx-action` is used to set up Docker Buildx, which is an extended version of the Docker CLI that supports building multi-platform images and utilizing advanced features like caching, building images concurrently, and leveraging external builders.
4. **Build and Push Docker Image**
   
5. The `docker/build-push-action` is used to build and push Docker images to a Docker registry, such as Docker Hub, as part of your CI pipeline.

## Decisions, Assumptions, and Challenges Faced
**Decisions**
- Containerization: Docker was chosen for easy portability and consistent deployment.

**Assumptions**
- The host machine has a stable internet connection for Docker image pulls and Minikube setup.
