from abc import ABC, abstractmethod

class Service(ABC):
    response = {'status': 200, 'message': '', 'data': None}
            
    @staticmethod
    @abstractmethod
    def store() -> None:
        pass
    @staticmethod
    @abstractmethod
    def index(username: str = None) -> list:
        pass