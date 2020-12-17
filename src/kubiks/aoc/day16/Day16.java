package kubiks.aoc.day16;

import kubiks.aoc.utils.FileReaderUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class Day16 {
    List<TicketField> fields = new ArrayList<>();
    Ticket ownTicket = null;
    List<Ticket> tickets = new ArrayList<>();

    Map<TicketField, Set<Integer>> positionCandidates = new HashMap<>();

    public Day16(List<String> data) {
        boolean otherTicketsStarted = false;
        for (String s: data) {
            if (s.matches(".+: .+ or .+")) {
                TicketField field = new TicketField(s);
                fields.add(field);
                positionCandidates.put(field, new HashSet<>());
            } else if (s.matches("[0-9].*")) {
                if (otherTicketsStarted) {
                    tickets.add(new Ticket(s));
                } else {
                    ownTicket = new Ticket(s);
                }
            } else if ("nearby tickets:".equals(s)) {
                otherTicketsStarted = true;
            }
        }
        for (Set<Integer> candidates : positionCandidates.values()) {
            for (int pos =0; pos <fields.size(); pos++) {
                candidates.add(pos);
            }
        }
    }

    public List<TicketField> getFields() {
        return fields;
    }

    public Ticket getOwnTicket() {
        return ownTicket;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    int sumOfInvalidFields(Ticket ticket) {
        int sum = 0;
        for (int value : ticket.getValues()) {
            boolean valid = false;
            for (TicketField field : fields) {
                if (field.valueValidForField(value)) {
                    valid = true;
                    break;
                }
            }
            if (!valid) {
                sum += value;
            }
        }
        return sum;
    }

    boolean allTicketFieldsValid(Ticket ticket) {
        for (int value : ticket.getValues()) {
            boolean valid = false;
            for (TicketField field : fields) {
                if (field.valueValidForField(value)) {
                    valid = true;
                    break;
                }
            }
            if (!valid) {
                return false;
            }
        }
        return true;
    }

    public int solvePart1() {
        int sum = 0;
        for (Ticket ticket : tickets) {
            sum += sumOfInvalidFields(ticket);
        }
        return sum;
    }

    void filterCandidatesBasedOnTicket(Ticket ticket) {
        for (int pos=0; pos<ticket.getValues().size(); pos++) {
            for (TicketField field: fields) {
                if (!positionCandidates.get(field).contains(pos)) {
                    continue;
                }
                if (!field.valueValidForField(ticket.getValues().get(pos))) {
                    positionCandidates.get(field).remove(pos);
                }
            }
        }
    }

    boolean fieldNameMatchesPart2(TicketField field, String fieldBeginString) {
        return field.getName().matches(fieldBeginString + ".*");
    }

    public long solvePart2(String fieldBeginString) {
        long product = 1L;
        for (Ticket ticket: tickets) {
            if (!allTicketFieldsValid(ticket)) {
                continue;
            }
            filterCandidatesBasedOnTicket(ticket);
        }
        filterCandidatesBasedOnTicket(ownTicket);
        Map<TicketField, Integer> foundFields = new HashMap<>();
        while (foundFields.size() < fields.size()) {
            TicketField fieldWithSingleCandidate = null;
            for (TicketField field: fields.stream().filter(f -> !foundFields.containsKey(f)).collect(Collectors.toSet())) {
                if (foundFields.containsKey(field)) {
                    continue;
                }
                if (positionCandidates.get(field).size() == 1) {
                    fieldWithSingleCandidate = field;
                    break;
                }
            }
            if (fieldWithSingleCandidate == null) {
                System.out.println("Stuck!");
                product = -1;
                break;
            }
            int pos = positionCandidates.get(fieldWithSingleCandidate).iterator().next();
            foundFields.put(fieldWithSingleCandidate, pos);
            for (TicketField field: fields) {
                if (field != fieldWithSingleCandidate) {
                    positionCandidates.get(field).remove(pos);
                }
            }
            if (fieldNameMatchesPart2(fieldWithSingleCandidate, fieldBeginString)) {
                product = product * ownTicket.getValues().get(pos);
            }
        }
        return product;
    }

    public static void main(String[] args) {
        Day16 day16 = new Day16(FileReaderUtils.readStringListFromFile("resources/day16_input.txt"));
        int answerPart1 = day16.solvePart1();
        System.out.format("Sum of invalid fields: %d\n", answerPart1);
        long answerPart2 = day16.solvePart2("departure");
        System.out.format("Product of departures in own ticket: %d\n", answerPart2);
    }
}
