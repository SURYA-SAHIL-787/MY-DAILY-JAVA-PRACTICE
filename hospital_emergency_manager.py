# File Name: hospital_emergency_manager.py

import heapq
from collections import deque


class HospitalManager:
    def __init__(self):
        # Heap for emergency patients
        self.emergency_heap = []

        # Queue for normal patients
        self.normal_queue = deque()

        # Dictionary for fast patient searching
        self.patient_records = {}

        # Set for duplicate prevention
        self.patient_ids = set()

        self.arrival_number = 0

    def add_patient(self, patient_id, name, condition, priority):
        if patient_id in self.patient_ids:
            print(f"Patient ID {patient_id} already exists.")
            return

        if priority < 1 or priority > 5:
            print("Priority must be between 1 and 5.")
            return

        self.arrival_number += 1

        patient = {
            "id": patient_id,
            "name": name.strip().title(),
            "condition": condition.strip().title(),
            "priority": priority
        }

        self.patient_records[patient_id] = patient
        self.patient_ids.add(patient_id)

        if priority <= 3:
            # Smaller priority number means more serious condition.
            heapq.heappush(
                self.emergency_heap,
                (
                    priority,
                    self.arrival_number,
                    patient_id
                )
            )
            queue_type = "emergency queue"
        else:
            self.normal_queue.append(patient_id)
            queue_type = "normal queue"

        print(
            f"{patient['name']} added to the {queue_type}."
        )

    def treat_next_patient(self):
        patient_id = None

        if self.emergency_heap:
            _, _, patient_id = heapq.heappop(
                self.emergency_heap
            )
        elif self.normal_queue:
            patient_id = self.normal_queue.popleft()
        else:
            print("No patients are waiting.")
            return

        patient = self.patient_records.pop(patient_id)
        self.patient_ids.remove(patient_id)

        print("\nPatient Being Treated")
        print(f"ID       : {patient['id']}")
        print(f"Name     : {patient['name']}")
        print(f"Condition: {patient['condition']}")
        print(f"Priority : {patient['priority']}")

    def search_patient(self, patient_id):
        patient = self.patient_records.get(patient_id)

        if patient is None:
            print("Patient not found.")
            return

        print("\nPatient Record")

        for key, value in patient.items():
            print(f"{key.title():10}: {value}")

    def display_waiting_patients(self):
        if not self.patient_records:
            print("No patients are currently waiting.")
            return

        print("\nEmergency Patients")

        emergency_copy = self.emergency_heap.copy()
        heapq.heapify(emergency_copy)

        if not emergency_copy:
            print("None")
        else:
            while emergency_copy:
                _, _, patient_id = heapq.heappop(
                    emergency_copy
                )

                patient = self.patient_records[patient_id]

                print(
                    f"{patient['id']} - {patient['name']} - "
                    f"{patient['condition']} - "
                    f"Priority {patient['priority']}"
                )

        print("\nNormal Patients")

        if not self.normal_queue:
            print("None")
        else:
            for patient_id in self.normal_queue:
                patient = self.patient_records[patient_id]

                print(
                    f"{patient['id']} - {patient['name']} - "
                    f"{patient['condition']}"
                )


def main():
    hospital = HospitalManager()

    hospital.add_patient(
        1001,
        "Rahul",
        "High fever",
        4
    )

    hospital.add_patient(
        1002,
        "Ananya",
        "Breathing difficulty",
        1
    )

    hospital.add_patient(
        1003,
        "Vikram",
        "Minor injury",
        5
    )

    hospital.add_patient(
        1004,
        "Priya",
        "Severe chest pain",
        2
    )

    hospital.add_patient(
        1005,
        "Arjun",
        "Headache",
        4
    )

    hospital.display_waiting_patients()
    hospital.search_patient(1004)

    hospital.treat_next_patient()
    hospital.treat_next_patient()
    hospital.treat_next_patient()

    hospital.display_waiting_patients()


if __name__ == "__main__":
    main()
