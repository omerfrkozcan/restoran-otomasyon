
1 - Sunucuda Docker kurulumu yapılır:
```
sudo apt-get remove docker docker-engine docker.io containerd runc
sudo apt-get update
sudo apt-get install ca-certificates curl gnupg
sudo install -m 0755 -d /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
sudo chmod a+r /etc/apt/keyrings/docker.gpg
echo "deb [arch="$(dpkg --print-architecture)" signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu "$(. /etc/os-release && echo "$VERSION_CODENAME")" stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin docker-compose
sudo docker network create restoran
```

2 - Git Repo klonlanır:
```
git clone git@github.com:omerfrkozcan/restoran-otomasyon.git
```

3 - Docker Compose build işlemi yapılır:
```
docker-compose build --no-cache restoran
```

4 - Docker Compose run işlemi yapılır (postgres-restoran tek seferlik çalıştırılır):
```
docker-compose up -d --no-deps --force-recreate postgres-restoran
docker-compose up -d --no-deps --force-recreate restoran
```


