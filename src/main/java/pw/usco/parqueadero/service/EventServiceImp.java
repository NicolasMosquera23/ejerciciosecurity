package pw.usco.parqueadero.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pw.usco.parqueadero.entity.EventEntity;
import pw.usco.parqueadero.repository.EventRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImp implements EventService<EventEntity, Long> {
    private final EventRepository eventRepository;

    @Override
    public List<EventEntity> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public EventEntity getById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found!"));
    }

    @Override
    public void create(EventEntity eventEntity) {
        eventRepository.save(eventEntity);
    }

    @Override
    public void update(Long id, EventEntity eventEntity) {
        EventEntity eventDB = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found!"));
        eventDB.setPlaca(eventEntity.getPlaca());
        eventDB.setHoraEntrada(eventEntity.getHoraEntrada());
        eventDB.setHoraSalida(eventEntity.getHoraSalida());
        eventRepository.save(eventDB);
    }

    @Override
    public void delete(Long id) {
        eventRepository.deleteById(id);
    }
}

