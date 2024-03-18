from abc import ABC
class Model(ABC):
    def toJson(self) -> dict:
        attributes = [attr for attr in dir(self) if not callable(getattr(self, attr)) and not attr.startswith("__")]
        response = {}
        for i in range(1, len(attributes)):
            response[attributes[i].lstrip('_')] = getattr(self, attributes[i])             
        return response
    
    def reverseJson(self, json: dict) -> None:
        for k, v in json.items():
            setattr(self, k, v)