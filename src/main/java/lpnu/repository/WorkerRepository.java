package lpnu.repository;

import lpnu.dto.WorkerDTO;
import lpnu.entity.Worker;
import lpnu.entity.mapper.DTOConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class WorkerRepository{
    private final Map<Long, Worker> employeeRepository = new HashMap<>();
    private final DTOConvertor dtoConvertor;

    private static long workerId = 0;

    @Autowired
    public WorkerRepository(DTOConvertor dtoConvertor) {
        this.dtoConvertor = dtoConvertor;
    }

    public WorkerDTO addWorker(final Worker worker){
        worker.setId(++workerId);
        employeeRepository.put(worker.getId(), worker);
        return dtoConvertor.convertToDto(worker, WorkerDTO.class);
    }

    public Worker getWorkerById(final Long id){
        return employeeRepository.getOrDefault(id, null);
    }
}
