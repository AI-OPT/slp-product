tagversion=v1.0_1
git reset --hard origin/master 
git pull 
chmod a+x onekey-docker.sh 
gradle clean && gradle build -x test 
docker build -t 10.19.13.18:5000/slp-product:${tagversion} .   
docker push 10.19.13.18:5000/slp-product:${tagversion} 

docker rmi aioptapp/slp-product:${tagversion} 
docker tag 10.19.13.18:5000/slp-product:${tagversion} aioptapp/slp-product:${tagversion} 
docker login --username=aioptapp --password=aioptapp@123 --email=wuzhen3@asiainfo.com 
docker push aioptapp/slp-product:${tagversion} 

