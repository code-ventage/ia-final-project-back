import datetime as dt
from repository import Repository
class UserScoreRepository(Repository):
    def store(username: str, score: int, time: int) -> None:
        with open('./Storage/user-score.txt', 'a') as file:
            file.write(f'{username} {score} {time} {dt.datetime.now()}\n')
            
    def index() -> dict:
        response = list()
        with open('./Storage/user-score.txt', 'r') as file:
            for f in file.readlines():
                s = f.replace('\n', '').split(' ')
                response.append({'nombre': s[0], 'score': s[1], 'time': s[2], 'data': s[3] + ' ' + s[4]})
                        
        return response

if __name__ == '__main__':
    UserScoreRepository.store('laos', 5, 5)
    print(UserScoreRepository.index())
