import sys
import os
dir_father = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.append(dir_father)

from repositories.repository import Repository
from exceptions.duplicateUser import DuplicateUser
import json
class UserRepository(Repository):
    def store(username: str, password: str) -> None:
        try:
            with open('./Storage/users.json', 'r') as file:
                users = json.load(file)
                
                user =  list(filter(lambda a: a['username'] == username, users))
                if len(user) > 0:
                    raise DuplicateUser() 
        except FileNotFoundError as e:
            users = []
    
        users.append({'username': username, 'password': password})
        
        with open('./Storage/users.json', 'w') as file:
            json.dump(users, file, indent=4)
    
    def index() -> tuple:
        with open('./Storage/users.json', 'r') as file:
            return json.load(file)
if __name__ == '__main__':
    # UserRepository.store('laos', '0205')
    # UserRepository.store('kratos', '0205')

    print(UserRepository.index())