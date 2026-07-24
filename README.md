git clone https://github.com/JhonR/inventario-mercado-Rios.git
cd inventario-mercado-Rios
cp .env.example .env
docker compose up -d --build
# API en http://localhost:8080/api/v1/productos
# Compilar el informe:
cd docs/informe && pdflatex informe && bibtex informe && pdflatex informe && pdflatex informe
