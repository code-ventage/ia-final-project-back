from repository import *
class UserRepository(Repository):
    def store(username: str, password: str) -> None:
        with open('./Storage/users.txt', 'a') as file:
            file.write(f'{username} {password}\n')
    
    def index() -> dict:
        response = list()
        with open('./Storage/users.txt', 'r') as file:
            for f in file.readlines():
                s = f.replace('\n', '').split(' ')
                response.append({'nombre': s[0], 'password': s[1]})
                        
        return response
    
if __name__ == '__main__':
    UserRepository.store('laos', '0205')
    print(UserRepository.index())