import os
import sys

dir_father = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.append(dir_father)
import json
from repositories.repository import Repository
from models.userScoreModel import userScoreModel
class UserScoreRepository(Repository):
    def store(userscore: userScoreModel) -> None:
        try:
            with open('scores.json', 'r') as file:
                scores = UserScoreRepository.index()
        except FileNotFoundError as e:
            scores = []
        
        scores.append(userscore)
        scoresJson = []
        
        for i in scores:
            scoresJson.append(i.toJson())
            
        with open('scores.json', 'w') as file:
            json.dump(scoresJson, file, indent=4)
    
    def index() -> list:
        scores = []
        try:
            with open('scores.json', 'r') as file:
                for i in json.load(file):
                    score = userScoreModel()
                    score.reverseJson(i)
                    scores.append(score)
        except FileNotFoundError as e:
            pass
                        
        return scores
                       

if __name__ == '__main__':
    UserScoreRepository.store(userScoreModel('laos', 5))
    UserScoreRepository.store(userScoreModel('kratos', 10))
    for i in UserScoreRepository.index():
        print(i.username)