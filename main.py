from fastapi import FastAPI

app = FastAPI()


@app.get("/")
def hello_world() -> str:
    return "Hello, world!"
