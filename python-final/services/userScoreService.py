from .service import Service
from ..repositories.userScoreRepository import UserScoreRepository as repository
from datetime import datetime as dt

class UserScoreService(Service):
    def create(username: str, score: int, time: int) -> None:
        repository.create(username, score, time)
        
    def index(username: str = None) -> list:
        response = repository.UserRepositoy.index(username)
        if username is None:
            return response
        else:
            return list(filter(lambda a: a['nombre'] == 'laos', response))
    
    def sortScore(self, username: str = None) -> list:
        response = self.index(username)
        response.sort(key=lambda x: (-int(x['score']), int(x['time']), dt.strptime(x['data'], '%Y-%m-%d %H:%M:%S.%f')))
        return response
        