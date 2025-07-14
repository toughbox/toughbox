

* 레지스트리
GitHub Container Registry (ghcr.io)가 GitHub에서 제공하는 Docker 이미지 레지스트리

* 운영 서버 설정
운영 서버에서 실행할 명령어들:

# 1. 프로젝트 클론
git clone https://github.com/your-username/toughbox.git /opt/toughbox
cd /opt/toughbox

# 2. 환경 변수 설정
cp .env.prod.example .env.prod
# .env.prod 파일을 실제 값으로 편집

# 3. 필요한 디렉토리 생성
mkdir -p nginx/ssl

# 4. 처음 배포
docker-compose -f docker-compose.prod.yml --env-file .env.prod up -d

# 5. 로그 확인
docker-compose -f docker-compose.prod.yml logs -f


