import sys
import os
dir_father = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.append(dir_father)
import json
from repositories.repository import Repository
class UserScoreRepository(Repository):
    def store(username: str, score: str, data: str) -> None:
        try:
            with open('./Storage/scores.json', 'r') as file:
                scores = json.load(file)
        except FileNotFoundError as e:
            scores = []
        
        scores.append({'username': username, 'score': score, 'data': data})
        
        with open('./Storage/scores.json', 'w') as file:
            json.dump(scores, file, indent=4)
    
    def index() -> dict:
        with open('./Storage/scores.json', 'r') as file:
            return json.load(file)
                       

if __name__ == '__main__':
    UserScoreRepository.store('laos', 5)
    UserScoreRepository.store('kratos', 10)
    print(UserScoreRepository.index())
