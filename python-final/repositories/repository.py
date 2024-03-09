from abc import ABC, abstractmethod
class Repository(ABC):
    @staticmethod
    @abstractmethod 
    def store() -> None:
        pass
    @staticmethod
    @abstractmethod
    def index() -> list:
        pass