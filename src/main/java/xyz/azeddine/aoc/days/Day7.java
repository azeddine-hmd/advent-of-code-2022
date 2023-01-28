package xyz.azeddine.aoc.days;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

import xyz.azeddine.aoc.Util;

public class Day7 extends DayBase {

	private final List<String> lines = Util.getInputLines("day7.txt");

	private final long FS_MAX_SIZE = 70_000_000;
	private final long FS_FREE_UPDATE_SIZE = 30_000_000;

	public Day7() {
		System.out.println("[Day7]");
	}

	@Override
	protected void part1() {
		DirectoryEntry rootDir = parseInput();
		List<Long> dirSizes = new ArrayList<Long>();
		forEachDirectory(rootDir, dir -> {
			long dirSize = dir.size();
			if (dirSize <= 100_000)
				dirSizes.add(dir.size());
		});
		long result = dirSizes.stream().mapToLong(n -> n).sum();
		System.out.println(result);
	}

	@Override
	protected void part2() {
		DirectoryEntry rootDir = parseInput();

		long target = Math.abs(FS_MAX_SIZE - rootDir.size() - FS_FREE_UPDATE_SIZE);

		List<Long> dirSizes = new ArrayList<>();
		forEachDirectory(rootDir, dir -> {
			long dirSize = dir.size();
			if (dirSize >= target)
				dirSizes.add(dirSize);
		});
		dirSizes.sort(Comparator.naturalOrder());
		System.out.println(dirSizes.stream().findFirst().get());
	}

	private void forEachDirectory(DirectoryEntry root, Consumer<DirectoryEntry> apply) {
		if (root == null)
			return;
		apply.accept(root);
		for (FileEntry fe : root.files) {
			if (fe instanceof DirectoryEntry dir) {
				forEachDirectory(dir, apply);
			}
		}
	}

	private DirectoryEntry parseInput() {
		DirectoryEntry rootDir = new DirectoryEntry(null, "/");
		DirectoryEntry currDir = rootDir;
		for (String line : lines) {
			String[] lnarr = line.split(" ");
			if (lnarr[0].equals("$") && lnarr[1].equals("cd")) {
				// handle cd
				if (!lnarr[2].equals("/")) {
					if (lnarr[2].equals("..")) {
						Optional<DirectoryEntry> parentDir = currDir.getParent();
						if (parentDir.isPresent())
							currDir = parentDir.get();
					} else {
						currDir = (DirectoryEntry) currDir.get(lnarr[2]);
					}
				}
			} else if (lnarr[0].equals("dir")) {
				// handle dir
				currDir.addDir(new DirectoryEntry(currDir, lnarr[1]));
			} else if (Util.isNumber(lnarr[0])) {
				// handle file
				currDir.addFile(new FileEntry(lnarr[1], Integer.parseInt(lnarr[0])));
			}
		}
		return rootDir;
	}

	private class DirectoryEntry extends FileEntry {

		private final LinkedList<FileEntry> files = new LinkedList<>();

		private final DirectoryEntry parent;

		public DirectoryEntry(DirectoryEntry parent, String name) {
			super(name, 0);
			this.parent = parent;
		}

		@Override
		public long size() {
			return files.stream().mapToLong(FileEntry::size).sum();
		}

		public Optional<DirectoryEntry> getParent() {
			return Optional.ofNullable(parent);
		}

		public void addDir(DirectoryEntry dir) {
			files.add(dir);
		}

		public void addFile(FileEntry fe) {
			files.add(fe);
		}
		
		public FileEntry get(String name) {
			return files.stream()
				.filter(f -> f.getName().equals(name))
				.findFirst()
				.get();
		}
	}

	private class FileEntry {
		private final String name;
		private final long size;

		public FileEntry(String name, long size) {
			this.name = name;
			this.size = size;
		}

		public String getName() {
			return name;
		}

		public long size() {
			return size;
		}
	}
}
