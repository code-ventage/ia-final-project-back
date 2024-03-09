from ..repositories.userScoreRepository import UserScoreRepository as repository
from .service import Service
class UserService(Service):
    def create(username: str, password: str) -> None:
        repository.UserRepositoy.create(username, password)
        
    def index(username: str = None) -> list:
        response = repository.UserRepositoy.index(username)
        if username is None:
            return response
        else:
            return list(filter(lambda a: a['nombre'] == 'laos', response))