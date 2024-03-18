import os
import sys

dir_father = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.append(dir_father)

from models.model import Model

class userModel(Model):
    def __init__(self, username: str = None, password: str =  None) -> None:
        super().__init__()
        self._username = username
        self._password = password
        
    def __eq__(self, __value: object) -> bool:
        if type(__value) == userModel :
            if __value.username == self.username:
                return True
            
        return False
        
    @property
    def username(self) -> str:
        return self._username
    @username.setter
    def username(self, username: str) -> None:
        self._username = username
        
    @property
    def password(self) -> str:
        return self._password
    @password.setter
    def password(self, password: str) -> None:
        self._password = password

if '__main__' == __name__:
    j = userModel('laos', '0205')
    print(j.toJson())
    i = userModel()
    i.reverseJson(j)

    
    