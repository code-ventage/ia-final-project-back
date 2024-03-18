import os
import sys

dir_father = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.append(dir_father)

from models.model import Model
from datetime import datetime as dt

class userScoreModel(Model):
    def __init__(self, username: str = None, score: int = None) -> None:
        super().__init__()
        self._username = username
        self._score = score
        self._data = str(dt.now())
        
    @property
    def username(self) -> str:
        return self._username
    @username.setter
    def username(self, username: str) -> None:
        self._username = username
        
    @property
    def score(self) -> int:
        return self._score
    @score.setter
    def score(self, score: int) -> None:
        self._score = score

if '__main__' == __name__:
    j = userScoreModel('laos', 5)
    print(j.toJson())
    
    